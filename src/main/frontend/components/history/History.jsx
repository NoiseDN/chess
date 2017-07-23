import React from 'react';

import './History.less';

class History extends React.Component {
    static propTypes = {
        history: React.PropTypes.array.isRequired
    };

    toEntry(entry, index) {
        const { record } = entry;

        return (
            <div key={index} className="history-entry">
                { index + 1 }: { record }
            </div>
        );
    }

    render() {
        const { history } = this.props;

        if (!history) {
            return false;
        }
        
        return (
            <section className="history-container">
                { history.map(this.toEntry) }
            </section>
        );
    }
}

export default History;