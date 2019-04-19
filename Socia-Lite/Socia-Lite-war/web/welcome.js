$(document).ready(function () {

    $(".btn-emoji").click(function () {
        var text = $(this).text();
        var box = $(".text-area-post");
        box.val(box.val() + text);
    });
    
    $('#img-upload').change(function() {
        var maximum;
        if(this.files.length > 5) {
            alert('Sorry, maximum 5 pictures per post');
            maximum = 5;
        } else {
            maximum = this.files.length;
        }
        
        for(var i = 0; i < maximum; i++) {
            document.getElementById('img-' + i).src = window.URL.createObjectURL(this.files[i]);
            $('.jumbotron-post').css('height', '350px');
            $('.jumbotron-post').hover(function() {
                $(this).css('height', '350px');
            }, function() {
                $(this).css('height', '130px');
            });
        }
        
        if(i === 0) {
            $('.jumbotron-post').css('height', '230px');
            $('.jumbotron-post').hover(function() {
                $(this).css('height', '230px');
            }, function() {
                $(this).css('height', '130px');
            });
            $('.img-preview').css('visibility', 'hidden');
        } else {
            $('.img-preview').css('visibility', 'visible');
        }
    });   
});


