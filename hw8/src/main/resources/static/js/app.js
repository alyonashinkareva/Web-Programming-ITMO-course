window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

window.notify_error = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "error"
    });
}
