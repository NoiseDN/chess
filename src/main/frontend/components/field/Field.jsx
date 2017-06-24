import React from 'react';

import Img from 'components/image/Img';

import './Field.less';

const CELL_COLOR = 'rgb(99, 99, 99)';
const SELECTION_COLOR = 'rgba(163, 233, 255, 0.5)';
const COORDINATES = [
    ['8', '7', '6', '5', '4', '3', '2', '1'],
    ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
];

class Cell {
    constructor(x, y, size, color) {
        this.color = color;
        this.size = size;
        this.left = x;
        this.right = x + size;
        this.top = y;
        this.bottom = y + size;
    }
}

class Field extends React.Component {
    static propTypes = {
        figures: React.PropTypes.array.isRequired
    };

    cells = [];
    selectedCell = null;

    componentDidMount() {
        this.drawCanvas();

        this.initCells();
    }

    componentWillReceiveProps() {
        this.reRender();
    }

    reRender() {
        this.drawCanvas();
        this.drawFigures();
    }

    initCells() {
        const { field } = this.refs;
        const blockSize = field.height / 8;

        for (let x = 0; x < field.height; x += blockSize) {
            for (let y = 0; y < field.height; y += blockSize) {
                const xeven = x % (blockSize * 2) === 0;
                const yeven = y % (blockSize * 2) === 0;

                if ((xeven && yeven) || (!xeven && !yeven)) {
                    this.cells.push(new Cell(x, y, blockSize, '#FFF'));
                } else {
                    this.cells.push(new Cell(x, y, blockSize, CELL_COLOR));
                }
            }
        }
    }

    drawCanvas() {
        const { field } = this.refs;

        const ctx = field.getContext('2d');

        // Reset canvas
        ctx.clearRect(0, 0, field.width, field.height);

        const blockSize = field.height / 8;
        const doubleCell = blockSize * 2;

        ctx.fillStyle = CELL_COLOR;

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

        // Draw selection
        const { selectedCell : cell } = this;

        if (cell !== null) {
            ctx.fillStyle = SELECTION_COLOR;
            ctx.fillRect(cell.left, cell.top, cell.size, cell.size);
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

    select(cell) {
        const { field } = this.refs;
        const ctx = field.getContext('2d');

        ctx.lineWidth = 2;
        ctx.strokeStyle = 'blue';
        ctx.strokeRect(cell.left, cell.top, cell.size, cell.size);
    }

    deselect(cell) {
        const { field } = this.refs;
        const ctx = field.getContext('2d');

        ctx.lineWidth = 2;
        ctx.strokeStyle = cell.color;
        ctx.strokeRect(cell.left, cell.top, cell.size, cell.size);
    }

    handleClick = (e) => {
        const clickedX = e.pageX - e.target.offsetLeft;
        const clickedY = e.pageY - e.target.offsetTop;
        const { cells } = this;

        cells.map(cell => {
            if (clickedX < cell.right && clickedX > cell.left && clickedY > cell.top && clickedY < cell.bottom) {
                if (this.selectedCell === cell) {
                    this.selectedCell = null;
                } else {
                    this.selectedCell = cell;
                }
                this.reRender();
            }
        });
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
                    onClick={this.handleClick}>
                </canvas>
                <Img onLoad={this.drawFigures} id="sprite" className="sprite" src="/assets/chess-sprite-small.png"/>
            </section>
        );
    }
}

export default Field;