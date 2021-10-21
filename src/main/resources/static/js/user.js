let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });

        $("#btn-login").on("click", () => {
            this.login();
        });

        $("#btn-update").on("click", () => {
            this.update();
        });
    },

    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            nickname: $("#nickname").val(),
            email: $("#email").val()
        };

        $.ajax({
            type: "post",
            url: "/api/users",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response) {
            alert("회원가입이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    // login: function () {
    //     let data = {
    //         username: $("#username").val(),
    //         password: $("#password").val(),
    //     };
    //
    //     $.ajax({
    //         type: "post",
    //         url: "/api/users/login",
    //         data: JSON.stringify(data),
    //         contentType: "application/json; charset=utf-8",
    //         dataType: "json"
    //     }).done(function (response) {
    //         alert("로그인이 완료되었습니다.");
    //         location.href = "/";
    //     }).fail(function (error) {
    //         alert(JSON.stringify(error));
    //     });
    // },

    update: function () {
        let data = {
            id: $("#id").val(),
            password: $("#password").val(),
            nickname: $("#nickname").val(),
            email: $("#email").val()
        };

        $.ajax({
            type: "put",
            url: "/api/users",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response) {
            alert(response.status);
            if (response.status === 500) {
                alert("회원수정이 실패하였습니다.");
            } else {
                alert("회원수정이 완료되었습니다.");
                location.href = "/";
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

}

index.init();