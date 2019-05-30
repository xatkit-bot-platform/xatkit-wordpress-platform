# xatkit-wordpress-platform
A Xatkit platform to receive events from WordPress



## Send a NewComment event

You can trigger a NewComment event by performing the following REST query on the Xatkit server:

URL: <xatkit server url>:<xatkit server port>

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

