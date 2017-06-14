var images = [];
var imagesSrc = [];
var chosenImage;

function insertImageClick() {
    let newImgElem = document.createElement('img');
    newImgElem.setAttribute('src', chosenImage);
    document.execCommand('insertHTML', false, '<br>' + newImgElem.outerHTML + '<br>');
}

function imageInputChange() {
    readURLOfInput(document.getElementById('imageInput'));
}

function readURLOfInput(input) {
    if (input.files && input.files[0]) {
        images.push(input.files[0]);
        var reader = new FileReader();

        reader.onload = function (e) {
            imagesSrc.push(e.target.result);
            let imgUrl = e.target.result;
            let newImgElem = document.createElement('img');
            newImgElem.setAttribute('src', imgUrl);
            newImgElem.setAttribute('id', 'img' + imagesSrc.length);
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
        };

        reader.readAsDataURL(input.files[0]);
    }
};

function initializeHistoryWithImages() {
    let historyText = document.getElementById('historyText');
    let usedImages = historyText.getElementsByTagName('img');
    for (let i = 0; i < usedImages.length; i++) {
        readURL(usedImages[i].getAttribute('src'));
    }
}

function readURL(url) {
    let newImgElem = document.createElement('img');
    newImgElem.setAttribute('src', url);
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

function imageUpload(id) {
    let imagesToSend = [];
    let historyText = document.getElementById('historyText');
    let usedImages = historyText.getElementsByTagName('img');
    for (let i = 0; i < usedImages.length; i++) {
        let usedImageSrc = usedImages[i].getAttribute('src');
        if (usedImageSrc.indexOf('resources\\images') == -1) {
            for (let j = 0; j < images.length; j++) {
                if (imagesSrc[j] == usedImageSrc)
                    imagesToSend.push(images[j]);
            }
        }
    }

    var data = new FormData();
    for (let i = 0; i < imagesToSend.length; i++)
        data.append('images[' + i + ']', imagesToSend[i]);
    let url = '/v2/history/';
    if (typeof id == 'number' || typeof id == 'string') url += id;
    url += '/images';
    $.ajax({
        type: 'post',
        url: url,
        processData: false,
        contentType: false,
        data: data,
        success: function (result) {
        },
        error: function (error) {
            console.log("error");
        }
    });

    return false;
}