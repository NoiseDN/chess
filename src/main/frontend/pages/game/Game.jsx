import React from 'react';
import { Link } from 'react-router';

import Field from 'components/field/Field';
import History from 'components/history/History';

import './Game.less';

class Game extends React.Component {
    static propTypes = {
        field: React.PropTypes.object,
        fetchField: React.PropTypes.func,
        moves: React.PropTypes.array,
        getPossibleMoves: React.PropTypes.func,
        move: React.PropTypes.bool,
        moveFigure: React.PropTypes.func,
        game: React.PropTypes.object,
        fetchGameStatus: React.PropTypes.func
    };

    componentWillMount() {
        this.refresh();
    }

    refresh() {
        const { id } = this.props.params;
        const { fetchField } = this.props;
        const { fetchGameStatus } = this.props;

        fetchField && fetchField(id);
        fetchGameStatus && fetchGameStatus(id);
    }

    moveFigure = (figureId, moveType, coordinates) => {
        const { moveFigure } = this.props;

        moveFigure && moveFigure(figureId, moveType, coordinates).then(() => {
            this.refresh();
        });
    };

    render() {
        const { field, getPossibleMoves, moves, game } = this.props;

        if (!field) {
            return (
                <section className="game">
                    Game is loading... please wait
                </section>
            );
        }

        return (
            <section className="game-container">
                <section className="header">
                    <Link to="/" className="button">Main menu</Link>
                    <span className="playerName">Player: {field.playerName} ({field.playWhites ? 'WHITE' : 'BLACK'})</span>
                    <span className="opponent">Opponent: AI ({field.playWhites ? 'BLACK' : 'WHITE'})</span>
                </section>
                <section className="game">
                    <Field
                        id={field.id}
                        game={game}
                        figures={field.figures}
                        getPossibleMoves={getPossibleMoves}
                        moveFigure={this.moveFigure}
                        moves={moves}
                        width="480"
                        height="480"/>
                    <History
                        history={field.history}/>
                </section>
            </section>
        );
    }
}

export default Game;