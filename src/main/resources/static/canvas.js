var COORDINATES = [
    ['8', '7', '6', '5', '4', '3', '2', '1'],
    ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
];

document.addEventListener('DOMContentLoaded', () => {
    var canvas = document.getElementById('tutorial');
    var ctx = canvas.getContext('2d');

    var blockSize = canvas.height / 8;
    var doubleCell = blockSize * 2;

    ctx.fillStyle = 'rgb(33, 33, 33)';

    // Draw field
    var even = true;

    for (var y = 0; y < canvas.height; y += blockSize) {
        var x;
        if (even) {
            for (x = blockSize; x < canvas.height; x += doubleCell) {
                ctx.fillRect(x, y, blockSize, blockSize);
            }
            even = false;
        } else {
            for (x = 0; x < canvas.height; x += doubleCell) {
                ctx.fillRect(x, y, blockSize, blockSize);
            }
            even = true;
        }
    }

    // Draw coordinates

    ctx.font = '12px Courier New';
    var center = 5;

    for (var y = 0, j = 0; y < canvas.height, j < 8; y += blockSize, j++) {
        for (var x = 0, i = 0; x < canvas.height, i < 8; x += blockSize, i++) {
            if (j === 0) {
                ctx.fillStyle = i % 2 === 0 ? 'black' : 'white';
                ctx.fillText(COORDINATES[1][i], (x + x + blockSize - center) / 2, y + 8);
            }
            if (j === 7) {
                ctx.fillStyle = i % 2 === 0 ? 'white' : 'black';
                ctx.fillText(COORDINATES[1][i], (x + x + blockSize - center) / 2, y + blockSize - 3);
            }
            if (i === 0) {
                ctx.fillStyle = j % 2 === 0 ? 'black' : 'white';
                ctx.fillText(COORDINATES[0][j], x + 1, (y + y + blockSize) / 2);
            }
            if (i === 7) {
                ctx.fillStyle = j % 2 === 0 ? 'white' : 'black';
                ctx.fillText(COORDINATES[0][j], x + blockSize - 10, (y + y + blockSize) / 2);
            }
        }
    }
});