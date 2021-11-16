import React, { Component } from 'react'

export default class Item extends Component {
    render() {
        return (
            <tr>
                <td>{this.props.item.name}</td>
                <td>{this.props.item.detail}</td>
                <td>{this.props.item.price}</td>
                <td>{this.props.item.stock}</td>
                <td>{this.props.item.itemStat}</td>
                <td>{this.props.item.regDateTime}</td>
            </tr>
        )
    }
}