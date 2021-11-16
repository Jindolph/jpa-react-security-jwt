import { List, Paper } from '@material-ui/core';
import React, { Component } from 'react';
import { call } from "./api/ApiService";

import Item from "./component/Item";

export default class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
        };
    }

    componentDidMount() {
        call("/items", "GET", null).then((response) =>
            this.setState({ items: response._embedded.items })
        );
    }

    render() {
        const items = this.state.items.length > 0 &&
            this.state.items.map((item, idx) =>
                <Item
                    item={item}
                    key={idx}
                />
            );

        return (
            <table>
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Item Detail</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Status</th>
                        <th>regDate</th>
                    </tr>
                </thead>
                <tbody>
                    {items}
                </tbody>
            </table>
        )
    }
}
