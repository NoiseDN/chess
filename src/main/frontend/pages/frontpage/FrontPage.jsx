import React from 'react';

import './FrontPage.less';

class FrontPage extends React.Component {
    state = {
        nickName: ''
    };

    componentWillMount() {
        const { fetchField } = this.props;

        fetchField && fetchField(true);
    }

    handleNickNameChange = (e) => {
        this.setState({
            nickName: e.target.value
        });
    };

    startGame = () => {
        const { nickName } = this.state;

        console.log('Game has started for ' + nickName);
    };

    render() {
        const { field } = this.props;
        const { nickName } = this.state;

        if (field) {
            console.log(field);
        }

        return (
            <section className="front">
                <h2>Chess</h2>
                <p>
                    by Anton Filimonov
                </p>

                <label htmlFor="nick-name">Name</label>
                <input
                    type="text"
                    id="nick-name"
                    className="nick-name"
                    value={nickName}
                    onChange={this.handleNickNameChange} />

                <button
                    className="start-game"
                    onClick={this.startGame}>
                    Start
                </button>

            </section>
        );
    }
}

export default FrontPage;