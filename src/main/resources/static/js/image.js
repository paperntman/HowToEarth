
$(function() {
    var params = {
        // Request parameters
        "application": "{string}",
    };

    const urlParams = new URL(location.href).searchParams;

    const name = urlParams.get('image');

    const serverURL = "http://topen0330.kro.kr/"

    $.ajax({
        url: "https://topen0330cv.cognitiveservices.azure.com/customvision/v3.0/Prediction/37744c03-6db3-47a3-9881-840d0bb21ac8/classify/iterations/Iteration3/url?" + $.param(params),
        beforeSend: function(xhrObj){
            // Request headers
            xhrObj.setRequestHeader("Content-Type","application/json");
            xhrObj.setRequestHeader("Prediction-key","57daae739b8f476b86264a0a45131e80");
        },
        type: "POST",
        // Request body
        data: '{"Url": "'+serverURL+'image/'+name+'"}',
    })
        .done(function(data) {
            let highest = data['predictions'][0];
            for (let i = 1; i < data['predictions'].length; i++) {
                let iElement = data['predictions'][i];
                let iOdd = iElement['probability'];
                let cOdd = highest['probability'];

                if (iOdd > cOdd) highest = iElement
            }

            document.getElementById("spinner").style.display = "none"
            document.getElementById(highest['tagName']).style.display = "unset"

        })
        .fail(function() {
            console.log("failed")
            document.getElementById("spinner").style.display = "none"
            document.getElementById("error").style.display = "unset"
        });
});