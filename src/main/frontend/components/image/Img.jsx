import React from 'react';

class Img extends React.Component {
    static propTypes = {
        src: React.PropTypes.string,
        onload: React.PropTypes.func
    };

    componentDidMount() {
        const { onLoad } = this.props;

        onLoad && onLoad();
    }

    render() {
        const { src, ...otherProps } = this.props;

        return (
            <img src={src} { ...otherProps } />
        );
    }
}

export default Img;