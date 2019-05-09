$(document).ready(function () {

    $('#radioBtn a').on('click', function(){
        var sel = $(this).data('title');
        var tog = $(this).data('toggle');
        $('#'+tog).prop('value', sel);

        $('a[data-toggle="'+tog+'"]').not('[data-title="'+sel+'"]').removeClass('active').addClass('notActive');
        $('a[data-toggle="'+tog+'"][data-title="'+sel+'"]').removeClass('notActive').addClass('active');
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


