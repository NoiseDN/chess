import React from 'react';

import Field from 'components/field/Field';

class Game extends React.Component {
    componentWillMount() {
        const { id } = this.props.params;

        const { fetchField } = this.props;

        fetchField && fetchField(id);
    }

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
                    moves={moves}
                    width="480"
                    height="480"/>
            </section>
        );
    }
}

export default Game;