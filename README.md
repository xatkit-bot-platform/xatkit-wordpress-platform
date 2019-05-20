# jarvis-wordpress-platform
A Jarvis platform to receive events from WordPress



## Send a NewComment event

You can trigger a NewComment event by performing the following REST query on the Jarvis server:

URL: <jarvis server url>:<jarvis server port>

Type: POST

Headers:

- Content Type: application/json
- WordPress-Event: comment

Body:

```json
{
	action : "created",
	user : "gwendal",
	comment : "A beautiful comment",
	post_title : "An amazing post"
}
```

