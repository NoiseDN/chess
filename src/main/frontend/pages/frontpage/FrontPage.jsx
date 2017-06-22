import React from 'react';

import Field from 'components/field/Field';

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

        if (!field) {
            return (
                <section className="front">
                    Loading...
                </section>
            );
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

                <Field figures={field} width="480" height="480"/>

            </section>
        );
    }
}

export default FrontPage;