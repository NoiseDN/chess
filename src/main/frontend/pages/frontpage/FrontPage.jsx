import React from 'react';
import { Link, browserHistory } from 'react-router';

import './FrontPage.less';

class FrontPage extends React.Component {
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

    startNewGame = () => {
        const { createField } = this.props;

        createField && createField(true);
    };

    toSavedGame = (field, index) => {
        return (
            <Link key={index} to={`/game/${field.id}`} className="saved-game">
                { field.id }
            </Link>
        );
    };

    render() {
        const { fields } = this.props;
        // const { nickName } = this.state;

        return (
            <section className="front">
                <h2>Chess</h2>
                <p>
                    by Anton Filimonov
                </p>

                <section className="new-game">
                    <h3>Start a new game</h3>
                    {/*<label htmlFor="nick-name">Name</label>*/}
                    {/*<input*/}
                        {/*type="text"*/}
                        {/*id="nick-name"*/}
                        {/*className="nick-name"*/}
                        {/*value={nickName}*/}
                        {/*onChange={this.handleNickNameChange} />*/}

                    <button
                        className="start-game"
                        onClick={this.startNewGame}>
                        Start New Game
                    </button>
                </section>

                { fields &&
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