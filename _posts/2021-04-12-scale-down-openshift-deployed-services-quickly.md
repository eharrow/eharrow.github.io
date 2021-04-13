---
layout: post
title: Scale Down Openshift Deployed Services Quickly
comments: false

tags: openshift kubernetes cli

---
I recently had to decommission a couple of live service which meant disabling certain aspects before the production region was decomissioned by others.  This however left pre-production still running in two regions which equated to about 2\*2\*35=140 services that I had to shutdown.  Using the openshift UI is too slow and clunky so I opted for the right way which is to use the `oc` cli.

The following script assumes one has logged into the correct openshift environment and switched to the appropriate project or namespace.  It queries for a list of deployment objects, extracts the name before iterating over the list scaling each down to zero pods.

```bash
#!/bin/bash
{% raw %}
deployments=`oc get dc -o template --template '{{range .items}}{{.metadata.name}}{{"\n"}}{{end }}'`
{% endraw %}
for dc in "${deployments[@]}"
do
  # echo -e ${dc}
  # scale down app to 0
  oc scale dc ${dc} --replicas=0
done

```
