Xatkit WordPress Platform
=====

[![License Badge](https://img.shields.io/badge/license-EPL%202.0-brightgreen.svg)](https://opensource.org/licenses/EPL-2.0)
[![Wiki Badge](https://img.shields.io/badge/doc-wiki-blue)](https://github.com/xatkit-bot-platform/xatkit/wiki/Xatkit-WordPress-Platform)

⚠ This platform is outdated and not actively supported anymore.

Receive events and perform actions on [WordPress](https://fr.wordpress.com/) in Xatkit execution models. 

## Providers

The WordPress platform define the following providers:

| Provider                   | Type  | Context Parameters | Description                                                  |
| -------------------------- | ----- | ------------------ | ------------------------------------------------------------ |
| RestProvider | Event | -                  | Receive webhook events from the WordPress and translate them into Xatkit-compatible events. |

### RestProvider Events

| Event                               | Context                | Parameters | Description                                                  |
| ----------------------------------- | ---------------------- | ---------- | ------------------------------------------------------------ |
| NewComment                        | `comment`                | - `action`: the action associated to the comment (e.g. `"created"`)<br/>- `user`: the user who created a new comment<br/>- `comment`: the content of the comment<br/>- `post_title`: the title of the post where the comment has been sent       | Event sent when a new comment is posted on a post    |

## Actions

The WordPress platform does not define any action.

## Options

The WordPress platform does not support any configuration option.
