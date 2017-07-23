function createSymbols(entity) {
    return {
        REQUEST: Symbol(`Fetch ${entity} request`),
        SUCCESS: Symbol(`Fetch ${entity} success`),
        ERROR: Symbol(`Fetch ${entity} error`)
    };
}

module.exports = {
    FIELD : createSymbols('Field'),
    FIGURE : createSymbols('Figure'),
    FIELDS : createSymbols('Fields'),
    MOVES : createSymbols('Moves'),
    MOVE : createSymbols('Move'),
    GAME : createSymbols('Game'),
    AI : createSymbols('AI')
};