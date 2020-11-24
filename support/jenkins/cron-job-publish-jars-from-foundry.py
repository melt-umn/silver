#!/usr/bin/env python3

# */10 * * * * /lhome/gitbot/cron-job-publish-jars-from-foundry
#
# Note that this currently does not self-update, or check for timestamps.

import json
from os import environ
from os.path import join
from subprocess import check_output
import sys
from tempfile import TemporaryFile
from urllib.request import Request, urlopen

WEB_STORE = environ.get("WEB_STORE", "/web/research/melt.cs.umn.edu/downloads/silver-dev")
SLACK_WEBHOOK_URL = environ.get("SLACK_WEBHOOK_URL", None)

files = [
    "CopperCompiler.jar",
    "CopperRuntime.jar",
    "IDEPluginRuntime.jar",
    "SilverRuntime.jar",
    "edu.umn.cs.melt.exts.silver.ableC.composed.with_ableC.jar",
    "edu.umn.cs.melt.exts.silver.ableC.composed.with_all.jar",
    "edu.umn.cs.melt.exts.silver.ableC.composed.with_base.jar",
    "gson-2.8.0.jar",
    "jeromq-0.3.4.jar",
    "silver-latest.tar.gz",
    "silver.composed.Default.jar",
]

if SLACK_WEBHOOK_URL is None:
    try:
        with open("/lhome/gitbot/cron-job-publish-jars-from-foundry-slack-webhook-url.text") as f:
            SLACK_WEBHOOK_URL = f.read().strip()
    except:
        pass

def send_to_slack(fmt, *args):
    if SLACK_WEBHOOK_URL is None:
        print(fmt.format(*args), file=sys.stderr)
    else:
        urlopen(Request(SLACK_WEBHOOK_URL,
            data=json.dumps({"text": fmt.format(*args)}).encode("utf-8"),
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
            resp = urlopen("https://foundry.remexre.xyz/custom-stable-dump/" + name)
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
