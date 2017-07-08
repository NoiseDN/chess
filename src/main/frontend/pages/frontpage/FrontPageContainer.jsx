import { connect } from 'react-redux';

import FrontPage from './FrontPage';
import { fetchFields } from 'ducks/fields';
import { createField } from 'ducks/field';

const mapStateToProps = ({ fields, field }) => ({
    fields, field
});

const mapDispatchToProps = (dispatch) => ({
    fetchFields: () => dispatch(fetchFields()),
    createField: (playWhites, nickName) => dispatch(createField(playWhites, nickName))
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(FrontPage);