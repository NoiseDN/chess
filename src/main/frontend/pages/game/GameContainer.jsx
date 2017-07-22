import { connect } from 'react-redux';

import Game from './Game';
import { fetchField } from 'ducks/field';
import { getPossibleMoves } from 'ducks/moves';
import { moveFigure } from 'ducks/move';
import { fetchGameStatus } from 'ducks/game';

const mapStateToProps = ({ field, moves, move, game }) => ({
    field, moves, move, game
});

const mapDispatchToProps = (dispatch) => ({
    fetchField: (id) => dispatch(fetchField(id)),
    getPossibleMoves: (fieldId, figure) => dispatch(getPossibleMoves(fieldId, figure)),
    moveFigure: (figureId, moveType, coordinates) => dispatch(moveFigure(figureId, moveType, coordinates)),
    fetchGameStatus: (fieldId) => dispatch(fetchGameStatus(fieldId))
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Game);