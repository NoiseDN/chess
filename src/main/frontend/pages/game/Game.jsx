import React from 'react';

import Field from 'components/field/Field';
import History from 'components/history/History';

class Game extends React.Component {
    static propTypes = {
        field: React.PropTypes.object,
        fetchField: React.PropTypes.func,
        moves: React.PropTypes.array,
        getPossibleMoves: React.PropTypes.func,
        move: React.PropTypes.bool,
        moveFigure: React.PropTypes.func
    };

    componentWillMount() {
        this.refresh();
    }

    refresh() {
        const { id } = this.props.params;
        const { fetchField } = this.props;

        fetchField && fetchField(id);
    }

    moveFigure = (figureId, moveType, coordinates) => {
        const { moveFigure } = this.props;

        moveFigure && moveFigure(figureId, moveType, coordinates).then(() => this.refresh());
    };

    render() {
        const { field, getPossibleMoves, moves } = this.props;

        if (!field) {
            return (
                <section className="game">
                    Game is loading... please wait
                </section>
            );
        }

        return (
            <section className="game">
                <Field
                    id={field.id}
                    figures={field.figures}
                    getPossibleMoves={getPossibleMoves}
                    moveFigure={this.moveFigure}
                    moves={moves}
                    width="480"
                    height="480"/>
                <History
                    history={field.history}/>
            </section>
        );
    }
}

export default Game;