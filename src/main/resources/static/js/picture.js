window.onload = function() {
    // picture button
    $(".ex_file").click(function() {
        $("#photoFile").click();
    });

    // after selecting picture
    $("#photoFile").on('change', function() {
        var file = $(this)[0].files[0];
        if (file) {
            var formData = new FormData();
            formData.append('file', file);

            $.ajax({
                url: '/upload',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
                    console.log('File uploaded successfully:', response);
                    // Process the success response
                },
                error: function(error) {
                    console.error('Failed to upload file:', error);
                    // Process the error response
                }
            });
        }
    });
}
