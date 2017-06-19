import React from 'react';

import './FrontPage.less';

class FrontPage extends React.Component {
    componentWillMount() {
        const { fetchField } = this.props;

        fetchField && fetchField();
    }

    render() {
        const { field } = this.props;

        if (field) {
            console.log(field);
        }

        return (
            <section className="front">
                <h2>Chess</h2>
                <p>
                    by Anton Filimonov
                </p>
            </section>
        );
    }
}

export default FrontPage;