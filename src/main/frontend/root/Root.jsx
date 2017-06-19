import React from 'react';

class Root extends React.Component {
    render() {
        return (
            <section className="root">
                { this.props.children }
            </section>
        );
    }
}

export default Root;