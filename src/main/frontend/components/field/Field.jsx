import React from 'react';

import Img from 'components/image/Img';

import './Field.less';

const COORDINATES = [
    ['8', '7', '6', '5', '4', '3', '2', '1'],
    ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
];

class Field extends React.Component {
    static propTypes = {
        figures: React.PropTypes.array.isRequired
    };

    dragging = false;

    componentDidMount() {
        this.drawCanvas();
    }

    componentWillReceiveProps() {
        this.drawFigures();
    }

    drawCanvas() {
        const { field } = this.refs;

        const ctx = field.getContext('2d');

        const blockSize = field.height / 8;
        const doubleCell = blockSize * 2;

        ctx.fillStyle = 'rgb(99, 99, 99)';

        // Draw field
        let even = true;

        for (let y = 0; y < field.height; y += blockSize) {
            let x;

            if (even) {
                for (x = blockSize; x < field.height; x += doubleCell) {
                    ctx.fillRect(x, y, blockSize, blockSize);
                }
                even = false;
            } else {
                for (x = 0; x < field.height; x += doubleCell) {
                    ctx.fillRect(x, y, blockSize, blockSize);
                }
                even = true;
            }
        }

        // Draw coordinates
        ctx.font = '12px Courier New';
        const center = 5;

        for (let y = 0, j = 0; y < field.height, j < 8; y += blockSize, j++) {
            for (let x = 0, i = 0; x < field.height, i < 8; x += blockSize, i++) {
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
                    ctx.fillText(COORDINATES[0][j], x + 1, (y + y + blockSize + center) / 2);
                }
                if (i === 7) {
                    ctx.fillStyle = j % 2 === 0 ? 'white' : 'black';
                    ctx.fillText(COORDINATES[0][j], x + blockSize - 10, (y + y + blockSize + center) / 2);
                }
            }
        }
    }

    drawFigures = () => {
        const { figures } = this.props;

        if (!figures) {
            return false;
        }

        figures.map(figure => {
            const { figureType, color, coordinates } = figure;

            this.drawFigure(figureType, color, coordinates[0], coordinates[1]);
        });
    };

    drawFigure(type, color, x, y) {
        const { field } = this.refs;
        const ctx = field.getContext('2d');
        const sprite = document.getElementById('sprite');
        const h = field.height / 8;

        const spriteCoords = this.getSpriteCoords(type, color);

        ctx.drawImage(sprite,
                      spriteCoords.x * h,   // source x
                      spriteCoords.y * h,   // source y
                      h,                    // source width
                      h,                    // source height
                      x * h,                // destination x
                      y * h,                // destination y
                      h,                    // destination width
                      h                     // destination height
        );
    }

    getSpriteCoords(type, color) {
        if (['WHITE', 'BLACK'].includes(color)) {
            switch (type) {
                case 'Rook':
                    return color === 'WHITE' ? { x : 4, y : 0 } : { x : 4, y : 1 };
                case 'Pawn':
                    return color === 'WHITE' ? { x : 5, y : 0 } : { x : 5, y : 1 };
                case 'Bishop':
                    return color === 'WHITE' ? { x : 2, y : 0 } : { x : 2, y : 1 };
                case 'Knight':
                    return color === 'WHITE' ? { x : 3, y : 0 } : { x : 3, y : 1 };
                case 'Queen':
                    return color === 'WHITE' ? { x : 1, y : 0 } : { x : 1, y : 1 };
                case 'King':
                    return color === 'WHITE' ? { x : 0, y : 0 } : { x : 0, y : 1 };
                default:
                    throw new Error('unknown figure type: ' + type);
            }
        }

        throw new Error('unknown figure color: ' + color);
    }

    handleMouseMove = (e) => {
        e && e.preventDefault();

        if (this.dragging) {
            console.log('dragging...');
        }
    };

    handleMouseDown = (e) => {
        e && e.preventDefault();

        this.dragging = true;
    };

    handleMouseUp = (e) => {
        e && e.preventDefault();

        this.dragging = false;
    };

    render() {
        const { width, height } = this.props;
        
        return (
            <section className="field-container">
                <canvas
                    className="field"
                    id="field"
                    width={width}
                    height={height}
                    ref="field"
                    onMouseDown={this.handleMouseDown}
                    onMouseUp={this.handleMouseUp}
                    onMouseMove={this.handleMouseMove}>
                </canvas>
                <Img onLoad={this.drawFigures} id="sprite" className="sprite" src="/assets/chess-sprite-small.png"/>
            </section>
        );
    }
}

export default Field;