$(document).ready(function () {
    var images = [];
    var chosenImage;
    function readURL(input) {
        if (input.files && input.files[0]) {
            images.push(input.files[0]);
            var reader = new FileReader();

            reader.onload = function (e) {
                let imgUrl = e.target.result;
                let newImgElem = document.createElement('img');
                newImgElem.setAttribute('src', imgUrl);
                newImgElem.setAttribute('id', 'img' + images.length);
                newImgElem.setAttribute('style', 'max-width: 100px');
                let newButtonElem = document.createElement('button');
                newButtonElem.setAttribute('class', 'btn btn-default');
                newButtonElem.onclick = function () {
                    chosenImage = this.childNodes[0].attributes['src'].nodeValue;
                    console.log(this);
                };
                newButtonElem.appendChild(newImgElem);
                var imgContainer = document.getElementById('imageContainer');
                imgContainer.appendChild(newButtonElem);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#imageInput").change(function () {
        readURL(this);
    });
    $('#imageUpload').click(function (e) {
        e.preventDefault();
        var data = new FormData();
        for (let i=0;i<images.length;i++)
            data.append('images[' + i + ']', images[i]);
        $.ajax({
            type: 'post',
            url: '/history/images',
            processData: false,
            contentType: false,
            data: data,
            success: function (result) {
                let imgUrl = 'res/img/history/' + result + '.jpg';
                var newButtonElem = document.createElement('button');
                newButtonElem.setAttribute('type', 'button');
                newButtonElem.setAttribute('class', 'btn btn-default');
                newButtonElem.setAttribute('ng-click', '$ctrl.setChosenImage(\'' +
                    imgUrl + '\')');
                var newImgElem = document.createElement('img');
                newImgElem.setAttribute('src', imgUrl);
                newButtonElem.appendChild(newImgElem);
                var imgContainer = document.getElementById('imageContainer');
                imgContainer.appendChild(newButtonElem);
            },
            error: function (error) {
                console.log("error");
            }
        });

        return false;
    });
    $('#insertImgButton').mousedown(function (e) {
         e.preventDefault();
    });
    $('#insertImgButton').click(function (e) {
        let newImgElem = document.createElement('img');
        newImgElem.setAttribute('src', chosenImage);
        document.execCommand('insertHTML', false, '<br>' + newImgElem.outerHTML + '<br>');
    });
});