import { connect } from 'react-redux';

import FrontPage from './FrontPage';
import { fetchField } from 'ducks/field';

const mapStateToProps = ({ field }) => ({
    field
});

const mapDispatchToProps = (dispatch) => ({
    fetchField: (playWhites) => dispatch(fetchField(playWhites))
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(FrontPage);