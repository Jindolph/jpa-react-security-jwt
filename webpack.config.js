var path = require('path');

module.exports = {
    entry: './src/main/resources/static/js/index.js',
    devtool: 'inline-source-map',
    cache: true,
    mode: 'development',
    output: {
        path: path.resolve(__dirname, './src/main/resources/static/built/'),
        filename: 'bundle.js',
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }]
            }
        ]
    }
};