var stompClient = null;
$(function() {

    var socket = new SockJS('/hello');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function() {
        console.log('Web Socket is connected');
        stompClient.subscribe('/topic/update', function(message) {
            $("#message").text(message.body);
        });
    });
})
$(function() {
    $("form").on('submit', function(e) {
        e.initEvent();
    });
    $("#send").click(function() {
        stompClient.send("/app/update");
    });
});