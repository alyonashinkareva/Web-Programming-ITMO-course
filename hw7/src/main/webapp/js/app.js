window.notify = function (message, isErrorMessage) {
    $.notify(message, {
        position: "right bottom",
        isErrorMessage: isErrorMessage,
    });
}

window.ajax = function (data, $error) {
    $.ajax({
        type: "POST",
        dataType: "json",
        data,
        success: function (response) {
            if (response["error"]) {
                $error.text(response["error"]);
            } else {
                location.href = response["redirect"];
            }
        }
    })
}


// $.ajax({
//     type: "POST",
//     dataType: "json",
//     data: {
//         action: "register",
//         login,
//         password,
//         email
//     },
//     success: function (response) {
//         if (response["error"]) {
//             $error.text(response["error"]);
//         } else {
//             location.href = response["redirect"];
//         }
//     }
// });
//
// $.ajax({
//     type: "POST",
//     dataType: "json",
//     data: {
//         action: "enter",
//         login,
//         password
//     },
//     success: function (response) {
//         if (response["error"]) {
//             $error.text(response["error"]);
//         } else {
//             location.href = response["redirect"];
//         }
//     }
// })