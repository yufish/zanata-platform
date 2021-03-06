/**
 * Production build config.
 *
 * This should be optimised for production performance and a small download.
 * Builds with this config should fail on any error, including linting errors.
 */

var webpack = require('webpack')
var path = require('path')
var ExtractTextPlugin = require('extract-text-webpack-plugin')
var merge = require('webpack-merge')
var defaultConfig = require('./webpack.config.js')

module.exports = merge.smart(defaultConfig, {
  entry: {
    'bundle.legacy': './app/legacy'
  },
  cache: false,
  output: {
    filename: 'frontend.[name].min.js'
  },
  module: {
    loaders: [
      {
        test: /\.jsx?$/,
        babelrc: false
      },
      {
        test: /\.css$/,
        loader: ExtractTextPlugin.extract(
          'style',
          'css',
          'autoprefixer?browsers=last 2 versions'
        )
      },
      {
        test: /\.less$/,
        loader: ExtractTextPlugin.extract(
          'style',
          'css!less',
          'autoprefixer?browsers=last 2 versions'
        )
      }
    ]
  },
  plugins: [
    new ExtractTextPlugin('frontend.css'),
    new webpack.optimize.UglifyJsPlugin({
      compress: {
        warnings: false
      }
    }),
    new webpack.DefinePlugin({
      'process.env': {
        'NODE_ENV': JSON.stringify('production')
      }
    })
  ]
})
