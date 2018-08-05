document.addEventListener('DOMContentLoaded', function () {

    if (!String.prototype.startsWith) {
        String.prototype.startsWith = function (searchString, position) {
            position = position || 0;
            return this.indexOf(searchString, position) === position;
        };
    }

    var div = document.getElementById("content");
    var btn = document.getElementById("submit");
    var input = document.getElementById("input-url");

    btn.onclick = function () {
        if (input.value.trim())
            sendRequest();
    }

    /**
     * Sends a post request to the server containing the URL to shorten.
     *
     * @returns {undefined} void
     */
    var sendRequest = function () {
        var request = new XMLHttpRequest();
        var onload = function () {
            if (request.readyState === 4 && request.status === 200) {
                var newUrl = request.responseText;

                /**
                 * creates an HTML a element for providing a shortened link to the web page.
                 */
                var a = document.createElement("a");
                a.setAttribute("href", newUrl);
                if (newUrl.startsWith("http")) {
                    a.innerHTML = newUrl;
                } else {
                    console.log(document.location.href);
                    a.innerHTML = document.location.href + newUrl;
                }

                div.innerHTML = "";
                div.appendChild(a);
            }
        };
        request.open("POST", "add", true);
        request.setRequestHeader("Content-Type", "application/json");
        request.onload = onload;
        request.send(input.value);
    }
});
