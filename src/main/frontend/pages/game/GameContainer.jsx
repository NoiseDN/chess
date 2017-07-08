import { connect } from 'react-redux';

import Game from './Game';
import { fetchField } from 'ducks/field';
import { getPossibleMoves } from 'ducks/moves';

const mapStateToProps = ({ field, moves }) => ({
    field, moves
});

const mapDispatchToProps = (dispatch) => ({
    fetchField: (id) => dispatch(fetchField(id)),
    getPossibleMoves: (fieldId, figure) => dispatch(getPossibleMoves(fieldId, figure))
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Game);