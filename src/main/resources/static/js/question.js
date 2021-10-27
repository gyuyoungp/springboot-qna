let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });

        $("#btn-delete").on("click", () => {
            this.deleteById();
        });

        $("#btn-update").on("click", () => {
            this.update();
        });

        $("#btn-answer-save").on("click", () => {
            this.answerSave();
        });
    },

    save: function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: "post",
            url: "/api/questions",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response) {
            alert("글쓰기가 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    deleteById: function () {
        let questionId = $("#questionId").val();

        $.ajax({
            type: "delete",
            url: "/api/questions/" + questionId,
            dataType: "json"
        }).done(function (response) {
            alert("삭제가 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update: function () {

        let questionId = $("#questionId").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: "put",
            url: "/api/questions/" + questionId,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response) {
            alert("글수정이 완료되었습니다.");
            location.href = `/questions/${questionId}`;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    answerSave: function () {
        let data = {
            boardId: $("#boardId").val(),
            content: $("#answer-content").val()
        };

        $.ajax({
            type: "post",
            url: `/api/questions/${data.boardId}/answers`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response) {
            alert("댓글작성이 완료되었습니다.");
            location.href = `/questions/${data.boardId}`;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    answerDelete: function (questionId, answerId) {
        $.ajax({
            type: "delete",
            url: `/api/questions/${questionId}/answers/${answerId}`,
            dataType: "json"
        }).done(function (response) {
            alert("댓글 삭제가 완료되었습니다.");
            location.href = `/questions/${questionId}`;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
}

index.init();