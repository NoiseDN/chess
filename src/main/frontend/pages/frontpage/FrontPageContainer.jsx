import { connect } from 'react-redux';

import FrontPage from './FrontPage';
import { fetchField } from 'ducks/field';
import { getPossibleMoves } from 'ducks/moves';

const mapStateToProps = ({ field, moves }) => ({
    field, moves
});

const mapDispatchToProps = (dispatch) => ({
    fetchField: (playWhites) => dispatch(fetchField(playWhites)),
    getPossibleMoves: (figure) => dispatch(getPossibleMoves(figure))
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(FrontPage);