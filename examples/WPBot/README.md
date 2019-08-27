# WPBot Example
An example bot using the [SlackPlatform](https://github.com/xatkit-bot-platform/xatkit-releases/wiki/Xatkit-Slack-Platform) and [WordPressPlatform](https://github.com/xatkit-bot-platform/xatkit-releases/wiki/Xatkit-WordPress-Platform) to receive events from WordPress and display them in Slack.



## Installation

The WPBot needs to be deployed on Slack. You can check [this article](https://github.com/xatkit-bot-platform/xatkit-runtime/wiki/Deploying-chatbots#create-a-slack-app) to create a Slack app for Xatkit, and set its authentication token in `WPBot.properties`:

```properties
xatkit.slack.token = <Your Slack app token>
```

You can also set the channel used by the WPBot to post messages (the `general` channel is used by default):

```properties
slack.channel = general
```



## Run your bot

Start your bot by running the following commands:

```bash
cd $XATKIT/bin
./start-xatkit-windows.sh <path to WPBot.properties>
```

If you don't have a local installation of Xatkit you can check [this article](https://github.com/xatkit-bot-platform/xatkit-releases/wiki/Installation) to install and setup Xatkit on your machine.



## Test your bot

You can trigger a `NewComment` event by performing the following REST query on the Xatkit server:

URL: http://localhost:5000

Type: POST

Headers:

- `Content-Type: application/json`
- `WordPress-Event: comment`

Body:

```json
{
    action     : "created",
    user       : "xatkit",
    comment    : "A new comment",
    post_title : "A WordPress post"
}
```

The bot should send a message in the Slack channel you have configured containing the event's details.