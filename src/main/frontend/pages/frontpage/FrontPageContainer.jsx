import { connect } from 'react-redux';

import FrontPage from './FrontPage';
import { fetchFields } from 'ducks/fields';
import { createField } from 'ducks/field';
import { makeAiMove } from 'ducks/ai';

const mapStateToProps = ({ fields, field, ai }) => ({
    fields, field, ai
});

const mapDispatchToProps = (dispatch) => ({
    fetchFields: () => dispatch(fetchFields()),
    createField: (playWhites, nickName) => dispatch(createField(playWhites, nickName)),
    makeAiMove: (fieldId) => dispatch(makeAiMove(fieldId))
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(FrontPage);