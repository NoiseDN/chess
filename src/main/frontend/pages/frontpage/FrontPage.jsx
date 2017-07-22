import React from 'react';
import { Link, browserHistory } from 'react-router';

import './FrontPage.less';

class FrontPage extends React.Component {
    state = {
        playerName: 'Player'
    };

    componentWillMount() {
        const { fetchFields } = this.props;

        fetchFields && fetchFields();
    }

    componentWillReceiveProps(nextProps) {
        const { field } = nextProps;

        if (field && field.id) {
            browserHistory.push(`/game/${field.id}`);
        }
    }

    handlePlayerNameChange = (e) => {
        e && e.preventDefault();

        this.setState({
            playerName: e.target.value
        });
    };

    startNewGame = () => {
        const { createField } = this.props;
        const { playerName } = this.state;

        createField && createField(true, playerName);
    };

    toSavedGame = (field, index) => {
        if (field.game && field.game.over) {
            return false;
        }

        return (
            <Link key={index} to={`/game/${field.id}`} className="saved-game">
                { field.playerName }
            </Link>
        );
    };

    render() {
        const { fields } = this.props;
        const { playerName } = this.state;

        return (
            <section className="front">
                <h2>Chess</h2>
                <p>
                    by Anton Filimonov
                </p>

                <section className="new-game">
                    <h3>Start a new game</h3>
                    <label htmlFor="nick-name">Name</label>
                    <input
                        type="text"
                        id="player-name"
                        className="player-name"
                        value={playerName}
                        onChange={this.handlePlayerNameChange} />

                    <button
                        className="start-game"
                        onClick={this.startNewGame}>
                        Start New Game
                    </button>
                </section>

                { fields && fields.length > 0 &&
                    <section className="load-game">
                        <h3>Load saved game</h3>
                        { fields.map(this.toSavedGame) }
                    </section>
                }

            </section>
        );
    }
}

export default FrontPage;