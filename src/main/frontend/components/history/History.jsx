import React from 'react';
import moment from 'moment';

import './History.less';

class History extends React.Component {
    static propTypes = {
        history: React.PropTypes.array.isRequired
    };

    toEntry(entry, index) {
        const { record, timestamp } = entry;
        const time = moment(timestamp).format('HH:mm:ss');

        return (
            <div key={index} className="history-entry">
                <span className="timestamp">[{ time }]:</span>
                <span className="move">{ record }</span>
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