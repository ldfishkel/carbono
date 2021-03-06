$("#login-form").on("submit", (e) => {
    e.preventDefault();
    
    let request = {
        username : $("#username").val(),
        password : $("#password").val()
    }

    const handleFieldsErrors = (errors) => {
        let errorDisplay = $('<ul></ul>');

        Object.entries(errors).forEach(entry => {
            const [key, value] = entry;
            $("#" + key).addClass("error");
            errorDisplay.append("<li>" + value + "</li>");
        });

        showToast(errorDisplay, "error");
    }

    const handleError = (message) => {
        let errorDisplay = $('<h5><u><b>' + message + '</b></u></h5>');
        showToast(errorDisplay, "error");
    }

    $.ajax({
        url: "/login",
        method: "POST",
        data: JSON.stringify(request),
        success:function(response){
            let data = JSON.parse(response);
            location.href = data.uri
        },
        error: (response, jqXHR, textStatus, errorThrown) => {
            if (response.status === 400)
                handleFieldsErrors(JSON.parse(response.responseText));
            if (response.status > 400)
                handleError(response.responseText);
        }
    })
});

$("input").on("click", function(e) {
    $(this).removeClass("error");
});