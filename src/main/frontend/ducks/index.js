import { combineReducers } from 'redux';

import field from 'ducks/field';
import fields from 'ducks/fields';
import moves from 'ducks/moves';
import move from 'ducks/move';
import game from 'ducks/game';
import ai from 'ducks/ai';

export default combineReducers({
    field,
    fields,
    moves,
    move,
    game,
    ai
});