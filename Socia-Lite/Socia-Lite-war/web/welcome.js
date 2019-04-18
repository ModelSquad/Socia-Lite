$(document).ready(function () {
    $(".btn-emoji").click(function () {
        var text = $(this).text();
        var box = $(".text-area-post");
        box.val(box.val() + text);
    });
});




