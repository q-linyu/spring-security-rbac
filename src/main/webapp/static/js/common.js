$.ajax({
    url: '/user/getUserInfo',
    type: 'GET',
    dataType: 'JSON',
    success: function (res) {
        if (res.status === 200){
            $("#wel-username").html(res.data.username);
            $("#date").html(res.data.lastTime);
            $("#username-title").html(res.data.username);
            $("#userId_hidden").val(res.data.userId);
        }
    },
    error: function (e) {
        console.log(e);
    }
});