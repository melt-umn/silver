#!/usr/bin/env python3

# */10 * * * * /lhome/gitbot/cron-job-publish-jars-from-foundry.py
#
# Note that this currently does not self-update, or check for timestamps.

import json
from os import environ
from os.path import join
from subprocess import check_output
import sys
from tempfile import TemporaryFile
from urllib.request import Request, urlopen

WEB_STORE = environ.get(
    "WEB_STORE", "/web/research/melt.cs.umn.edu/downloads/silver-dev/jars")
SLACK_WEBHOOK_URL = environ.get("SLACK_WEBHOOK_URL", None)

files = [
    "CopperCompiler.jar",
    "IDEPluginRuntime.jar",
    "SilverRuntime.jar",
    "edu.umn.cs.melt.exts.silver.ableC.composed.with_ableC.jar",
    "edu.umn.cs.melt.exts.silver.ableC.composed.with_all.jar",
    "edu.umn.cs.melt.exts.silver.ableC.composed.with_base.jar",
    "silver-latest.tar.gz",
    "silver.compiler.composed.Default.jar",
]

if SLACK_WEBHOOK_URL is None:
    try:
        with open("/lhome/gitbot/cron-job-publish-jars-from-foundry-slack-webhook-url.txt") as f:
            # To recreate this file:
            #
            # 1. Be a collaborator on the corresponding Slack app.
            # 2. Go to https://api.slack.com/apps/A01F6CM5X46/install-on-team?
            # 3. SSH to coldpress
            # 4. sudo -u gitbot vim /lhome/gitbot/cron-job-publish-jars-from-foundry-slack-webhook-url.txt
            # 5. Hit Copy on the appropriate webhook from the Slack page from step 2
            # 6. Paste it into the file
            # 7. Google how to exit Vim
            SLACK_WEBHOOK_URL = f.read().strip()
    except:
        pass


def send_to_slack(fmt, *args):
    if SLACK_WEBHOOK_URL is None:
        print(fmt.format(*args), file=sys.stderr)
    else:
        urlopen(Request(SLACK_WEBHOOK_URL,
                        data=json.dumps(
                            {"text": fmt.format(*args)}).encode("utf-8"),
                        headers={"Content-type": "application/json"}))


try:
    old_hashes = None
    try:
        with open(join(WEB_STORE, "SHA256SUMS"), "rb") as f:
            old_hashes = f.read()
    except Exception:
        pass
    for name in files:
        with open(join(WEB_STORE, name), "wb") as f:
            resp = urlopen(
                "https://foundry.remexre.xyz/custom-stable-dump/" + name)
            f.write(resp.read())
    hashes = check_output(["sha256sum"] + files, cwd=WEB_STORE)
    updated = False
    if old_hashes != hashes:
        updated = True
    with open(join(WEB_STORE, "SHA256SUMS"), "wb") as f:
        f.write(hashes)
    if updated:
        send_to_slack("Copied jars!")
except Exception as e:
    send_to_slack("Caught exception `{}`", e)
