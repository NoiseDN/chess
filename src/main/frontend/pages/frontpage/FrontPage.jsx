import React from 'react';
import { browserHistory } from 'react-router';

import './FrontPage.less';

class FrontPage extends React.Component {
    state = {
        playerName: 'Player',
        color: 'WHITE'
    };

    componentWillMount() {
        const { fetchFields } = this.props;

        fetchFields && fetchFields();
    }

    componentWillReceiveProps(nextProps) {
        const { field } = nextProps;

        if (field && field.id) {
            window.setTimeout(() => browserHistory.push(`/game/${field.id}`), 200);
        }
    }

    handlePlayerNameChange = (e) => {
        e && e.preventDefault();

        this.setState({
            playerName: e.target.value
        });
    };

    handleFocus = (e) => {
        e && e.preventDefault();

        e.target.select();
    };

    handleColorChange = (e) => {
        this.setState({
            color: e.target.value
        });
    };

    startNewGame = () => {
        const { createField, makeAiMove } = this.props;
        const { playerName, color } = this.state;

        createField && createField(color === 'WHITE', playerName).then(field => {
            if (!field.playWhites) {
                makeAiMove && makeAiMove(field.id);
            }
        });
    };

    goTo = (e, url) => {
        e && e.preventDefault();

        browserHistory.push(url);
    };

    toSavedGame = (field, index) => {
        if (field.game && field.game.over) {
            return false;
        }

        const buttonText = `${field.playerName} ${field.playWhites ? '♙' : '♟'} vs AI ${field.playWhites ? '♟' : '♙'}`;

        return (
            <button key={index} onClick={(e) => this.goTo(e, `/game/${field.id}`)} className="saved-game">
                { buttonText }
            </button>
        );
    };

    render() {
        const { fields } = this.props;
        const { playerName, color } = this.state;

        return (
            <section className="front">
                <h2>Chess</h2>
                <p>
                    by Anton Filimonov
                </p>

                <fieldset className="new-game">
                    <legend><h3>Start a new game</h3></legend>
                    <label htmlFor="player-name">Name</label>
                    <input
                        type="text"
                        id="player-name"
                        className="player-name"
                        value={playerName}
                        onFocus={this.handleFocus}
                        onChange={this.handlePlayerNameChange} />
                    <label htmlFor="white" className="pawn">♙</label>
                    <input
                        type="radio"
                        name="color"
                        id="white"
                        value="WHITE"
                        checked={color === 'WHITE'}
                        onChange={this.handleColorChange}/>
                    <label htmlFor="black" className="pawn">♟</label>
                    <input
                        type="radio"
                        name="color"
                        id="black"
                        value="BLACK"
                        checked={color === 'BLACK'}
                        onChange={this.handleColorChange}/>
                    <button
                        className="start-game"
                        onClick={this.startNewGame}>
                        Start New Game
                    </button>
                </fieldset>

                { fields && fields.length > 0 &&
                    <fieldset className="load-game">
                        <legend><h3>Load saved game</h3></legend>
                        { fields.map(this.toSavedGame) }
                    </fieldset>
                }
            </section>
        );
    }
}

export default FrontPage;