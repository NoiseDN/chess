import React from 'react';
import moment from 'moment';
import classnames from 'classnames';

import './History.less';

class History extends React.Component {
    static propTypes = {
        history: React.PropTypes.array.isRequired
    };

    componentWillReceiveProps() {
        window.setTimeout(() => {
            const entryNodes = document.getElementsByClassName('history-entry');

            entryNodes[entryNodes.length - 1].classList.remove('new');
        }, 1000);
    }

    toEntry(entry, index, size) {
        const { record, timestamp } = entry;
        const time = moment(timestamp).format('HH:mm:ss');

        return (
            <div key={index} id={'history' + index} className={classnames('history-entry', { 'new': index === size - 1 })}>
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
                { history.map((e, i) => this.toEntry(e, i, history.length)) }
            </section>
        );
    }
}

export default History;