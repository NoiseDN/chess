import React from 'react';

import './Root.less';

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