import { connect } from 'react-redux';

import Game from './Game';
import { fetchField } from 'ducks/field';
import { getPossibleMoves } from 'ducks/moves';
import { moveFigure } from 'ducks/move';

const mapStateToProps = ({ field, moves, move }) => ({
    field, moves, move
});

const mapDispatchToProps = (dispatch) => ({
    fetchField: (id) => dispatch(fetchField(id)),
    getPossibleMoves: (fieldId, figure) => dispatch(getPossibleMoves(fieldId, figure)),
    moveFigure: (figureId, coordinates) => dispatch(moveFigure(figureId, coordinates))
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Game);