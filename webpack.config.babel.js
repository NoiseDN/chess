import webpack from 'webpack';
import path from 'path';
import autoprefixer from 'autoprefixer';

const APP_PATH      = path.join(__dirname, 'src/main');
const DIST_PATH     = path.join(APP_PATH, 'resources/static/dist');
const FRONTEND_PATH = path.join(APP_PATH, 'frontend');

export default {
    devtool: 'cheap-module-source-map',
    entry: {
        main: path.join(FRONTEND_PATH, 'app.jsx')
    },
    output: {
        path: DIST_PATH,
        publicPath: '/',
        filename: '[name].js',
        chunkFilename: '[name].js',
        sourceMapFilename: '[name].map'
    },
    resolve: {
        modules: [
            FRONTEND_PATH,
            'node_modules'
        ],
        extensions: ['.jsx', '.js']

    },
    module: {
        rules: [
            {
                test: /\.jsx?$/,
                exclude: /node_modules/,
                use: ['react-hot-loader', 'babel-loader', 'eslint-loader']
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.less$/,
                use: ['style-loader', 'css-loader', 'postcss-loader', 'less-loader']
            },
            {
                test: /\.(png|jpg|svg)$/,
                use: 'url-loader?limit=8192&name=images/[name].[ext]'
            },
            {
                test: /fonts\/.+\.(eot|ttf|woff|svg|svgz)$/,
                use: 'file-loader?name=fonts/[name].[ext]'
            }
        ]
    },
    plugins: [
        new webpack.DefinePlugin({
            'process.env': {
                'NODE_ENV': '"development"'
            },
            DEBUG: true
        }),
        new webpack.LoaderOptionsPlugin({
            options: {
                postcss: [autoprefixer({browsers: ['> 1%', 'last 2 versions', 'Firefox ESR', 'not ie < 10']})]
            }
        }),
    ]
}