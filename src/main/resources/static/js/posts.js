(function($) {
    let request = $.ajax({'url': '/posts.json'});
    request.done(function (posts) {
        let html = '';
        posts.forEach(function(post) {
            html += '<div>';
            html += '<h1>' + post.title + '</h1>';
            html += '<p>' + post.body + '</p>';
            html += '<p>Published by ' + post.user.username + '</p>';
            html += '</div>';
        });
        $('#posts').html(html);
    });
})(jQuery);